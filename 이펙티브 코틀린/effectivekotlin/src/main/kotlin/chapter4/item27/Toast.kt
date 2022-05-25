package chapter4.item27

class Text {
    fun show() {

    }
}
class Context {

}
class Toast {
    companion object {
        const val LENGTH_LONG = 7

        fun makeText(context: Context, message: String, duration: Int): Text {
            return Text()
        }
    }
}

fun Context.toast(
    message: String,
    duration: Int = Toast.LENGTH_LONG
) {
    Toast.makeText(this, message, duration).show()
}


fun Context.showMessage(
    message: String,
    duration: MessageLength = MessageLength.LONG,
) {
    val toastDuration = when (duration) {
        MessageLength.SHORT -> Length.LENGTH_LONG
        MessageLength.LONG -> Length.LENGTH_LONG
    }

    Toast.makeText(this, message, toastDuration).show()
}

enum class MessageLength {
    SHORT,
    LONG
    ;
}

class Length {
    companion object {
        const val LENGTH_SHORT = 5
        const val LENGTH_LONG = 10
    }
}