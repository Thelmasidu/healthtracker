import controllers.UserStore
import models.User
import utils.ValidationUtility

//var user = User()
val userStore = UserStore()

//Users Input/Output example
//fun main(){
//    println("Welcome to Health Tracker")
//    println("Please enter the following for the user:")
//    print("    Name: ")
//    user.name = readLine()!!
//    print("    Email: ")
//    user.email = readLine()!!
//    print("    Id: ")
//    user.id = readLine()?.toIntOrNull() ?: -1
//
//    print("The user details are: $user")
//}

//Adding functions
fun main(){
    println("Welcome to Health tracker")
//    This variable add users and also list the user in the function
//    addUser()
//    listUser()
    runApp()
//    NOTE: This variable already has the add and list user inside it's function, so there's no use calling out the add/lust user variable in the main function.
}

//fun addUser(){
//    println("Please enter the following for the user:")
//    print("     Name: ")
//    user.name = readln()
//    print("     Email: ")
//    user.email = readln()
//    print("     Weight is ")
//    user.weight = readln().toDouble()
//    print("     Height is ")
//    user.height = readln().toFloat()
//    print("     Gender:")
//    user.gender = readln()
//
//
//    user.id = readlnOrNull()?.toIntOrNull() ?: -1
//


fun addUser() {
    println("Please enter the following for the user:")

    val user = User()

    print("     Name: ")
    user.name = readln()

    do {
        print("     Email: ")
        user.email = readln()
        if (!ValidationUtility.validateEmail(user.email)) {
            println("Invalid email format. Please try again.")
        }
    } while (!ValidationUtility.validateEmail(user.email))

    do {
        print("     Weight: ")
        user.weight = readln().toDouble()
        if (!ValidationUtility.validateWeight(user.weight)) {
            println("Invalid weight. Please enter a value between 0 and 300 kg.")
        }
    } while (!ValidationUtility.validateWeight(user.weight))

    do {
        print("     Height: ")
        user.height = readln().toFloat()
        if (!ValidationUtility.validateHeight(user.height)) {
            println("Invalid height. Please enter a value between 0.5 and 2.5 meters.")
        }
    } while (!ValidationUtility.validateHeight(user.height))

    do {
        print("     Gender (M/F/O): ")
        user.gender = readln()
        if (!ValidationUtility.validateGender(user.gender)) {
            println("Invalid gender. Please enter 'M', 'F', or 'O'.")
        }
    } while (!ValidationUtility.validateGender(user.gender))


//    user.id = readlnOrNull()?.toIntOrNull() ?: -1

    userStore.create(user)
}






fun listUsers(){
//    println("The user details are: $user")
    println("The user details are: ${userStore.findAll()}")
}

//menu function
/** fun menu(): Int{
    println("\nMain Menu:")
    println("1. Add User")
    println("2. List User")
    println("0. Exit")
    print("Please enter your option: ")
    return readlnOrNull()?.toIntOrNull() ?: -1
} */

//This is a starter code calling for the adduser and listuser
//fun runApp(){
//    var input: Int
//    do {
//        input = menu()
//        when(input) {
//            1 -> addUser()
//            2 -> listUser()
//        }
//    } while (input != 0)
//}

/** This function  is to add more variables to the user. EG. we added 3-6 variables which will either show
invalid or feature soon
*/
fun runApp(){
    var input: Int
    do {
        input = menu()
        when(input) {
            1 -> addUser()
            2 -> listUsers()
            3 -> deleteUser()
            4 -> searchById()
            in(5..6) -> println("Feature coming soon")
            0 -> println("Bye...")
            else -> print("Invalid Option")
        }
    } while (input != 0)
}


fun menu(): Int{
    print("""
        |Main Menu:
        |  1. Add User
        |  2. List 
        |  3. Delete
        |  4. Search
        |  0. Exit
        |Please enter your option: """.trimMargin())
    return readlnOrNull()?.toIntOrNull() ?: -1
}

//THIS IS A HELPER FUNCTION
fun getUserById() : User?{
    print("Enter the id of the user: ")
    return  userStore.findOne(readlnOrNull()?.toIntOrNull() ?: -1)
}

fun deleteUser(){
    if (userStore.delete(getUserById()))
        println ("User deleted")
    else
        println ("No user")
}

fun searchById() {
    val user = getUserById()
    if (user == null)
        println ("No user found")
    else
        println(user)

    val input = null
    print("""
        |Main Menu:
        |  1. Add User
        |  2. List Users
        |  3. Search by Id
        |  0. Exit
""")

         when(input) {
            1 -> addUser()
            2 -> listUsers()
            3 -> searchById()
            in(4..6) -> println("Feature coming soon")
            0 -> println("Bye...")
            else -> print("Invalid Option")
        } }