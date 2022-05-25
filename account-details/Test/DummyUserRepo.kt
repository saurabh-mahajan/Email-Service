
package service
import exceptions.NotFoundException
import models.User
import repository.UserRepository


class DummyUserRepo : UserRepository{

    override fun findByEmail(email: String?) : User{
//        if(email=="saurabh.mahajan@medly.com"){
//            throw NotFoundException()
//        }
        return User("1234567890",  "saurabh.mahajan@medly.com",  "saurabh")
    }


}
