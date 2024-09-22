import models.User

var user = User()

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

fun addUser(){
    println("Please enter the following for the user:")
    print("     Name: ")
    user.name = readln()
    print("     Email: ")
    user.email = readln()
    user.id = readlnOrNull()?.toIntOrNull() ?: -1
}

fun listUser(){
    println("The user details are: $user")
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
            2 -> listUser()
            in(3..6) -> println("Feature coming soon")
            0 -> println("Bye...")
            else -> print("Invalid Option")
        }
    } while (input != 0)
}

fun menu(): Int{
    print("""
        |Main Menu:
        |  1. Add User
        |  2. List User
        |  0. Exit
        |Please enter your option: """.trimMargin())
    return readlnOrNull()?.toIntOrNull() ?: -1
}