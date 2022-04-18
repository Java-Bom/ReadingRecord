package item11

class Item11(
    val view: View = View(),
    val person: Person? = Person("a")
) {

    fun 직관적인_함수() {
        if (person != null && person.isAdult) {
            view.showPerson(person)
        } else {
            view.showError()
        }
    }

    fun 가독성이_떨어지는_함수() {
        person?.takeIf { it.isAdult }
            ?.let {
                view.showPerson(it)
                print("aaaa")
            } ?: run { view.showError() }
    }

}

class Person(val name: String, val isAdult: Boolean = true)

class View() {
    fun showPerson(person: Person): String? {
        print(person)
        return null
    }

    fun showError() {
        print("error")
    }
}