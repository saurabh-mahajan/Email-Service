//import dummyUserService.DummyUserService
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.ints.exactly
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import models.EmailBody
import models.User
import repository.UserRepository
import service.DummyUserRepo
import service.EmailService
import service.UserService


class userServiceTest : StringSpec() {
    init {
        "send the mail to respected mailer"{
//            val dummyEmailService = DummyEmailService()
//           // val
//            val userService =  UserService(null, dummyEmailService)
//            userService.sendWelcomeEmail("saurabh.mahajan@medly.com")
//            val expectedEmailBody = EmailBody("Welcome","Welcome to the portal","saurabh.mahajan@medly.com")
//            //val actualEmailBody = "saurabh.mahajan@medly.com"
//            val actualEmailBody  = dummyEmailService.getLastEmail()
//            actualEmailBody shouldBe expectedEmailBody
            val mockkEmailService = mockk<EmailService>(relaxed=true)
            val userService =  UserService(null, mockkEmailService)

            val expectedEmailBody = EmailBody("Welcome","Welcome to the portal","saurabh.mahajan@medly.com")
            userService.sendWelcomeEmail("saurabh.mahajan@medly.com")
            verify(exactly =1) {mockkEmailService.send(expectedEmailBody)}
            //UserService("hjj","ghgh").sendWelcomeEmail("saurabh.mahajan@medly.com") shouldBe true
        }
        // with mockk
        "should send account detail if user found"{
           // val dummyEmailService = DummyEmailService()
          //  val dummyUserRepo = DummyUserRepo()
            val mockkEmailService = mockk<EmailService>(relaxed=true)
            val mockkUserRepo = mockk<UserRepository>()
            every {mockkUserRepo.findByEmail("saurabh.mahajan@medly.com")} returns User("1234567890",  "saurabh.mahajan@medly.com",  "saurabh")
            var userRepo = UserService(mockkUserRepo,mockkEmailService)
            userRepo.sendRegisteredPhoneNumber("saurabh.mahajan@medly.com")
            var expectedEmailBody = EmailBody("Account Details","Here is your Registered Phone Number: 1234567890","saurabh.mahajan@medly.com")
            //var actualEmailBody = dummyEmailService.getLastEmail()
            //actualEmailBody shouldBe expectedEmailBody
            verify(exactly = 1) {mockkEmailService.send(expectedEmailBody)}
        }
        // without mockk
        "If email not found then don't send the account details"{
            val dummyEmailService = DummyEmailService()
            val dummyUserRepo = DummyUserRepo()
            var userRepo = UserService(dummyUserRepo,dummyEmailService)
            userRepo.sendRegisteredPhoneNumber("saurabh.mahajan@medly.com")
            var expectedEmailBody = EmailBody("Account Details","Here is your Registered Phone Number: 1234567890","saurabh.mahajan@medly.com")
            var actualEmailBody = dummyEmailService.getLastEmail()
            actualEmailBody shouldBe expectedEmailBody
        }
    }
}
