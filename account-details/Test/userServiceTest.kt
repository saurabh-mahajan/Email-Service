//import dummyUserService.DummyUserService
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import models.EmailBody
import service.UserService


class userServiceTest : StringSpec() {
    init {
        "send the mail to respected mailer"{
            val dummyEmailService = DummyEmailService()
            val userService =  UserService(null, dummyEmailService)
            userService.sendWelcomeEmail("saurabh.mahajan@medly.com")
            val expectedEmailBody = EmailBody("Welcome","Welcome to the portal","saurabh.mahajan@medly.com")
            //val actualEmailBody = "saurabh.mahajan@medly.com"
            val actualEmailBody  = dummyEmailService.getLastEmail()
            actualEmailBody shouldBe expectedEmailBody
            //UserService("hjj","ghgh").sendWelcomeEmail("saurabh.mahajan@medly.com") shouldBe true
        }
    }
}
