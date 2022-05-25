package chapter4.item27
//
//class MessageDisplay(
//    val context: Context
//) {
//
//    fun show(
//        message: String,
//        duration: MessageLength = MessageLength.LONG
//    )  {
//        val toastDuration = when (duration) {
//            MessageLength.SHORT -> Length.LENGTH_LONG
//            MessageLength.LONG -> Length.LENGTH_LONG
//        }
//
//        Toast.makeText(this.context, message, toastDuration).show()
//    }
//}
//
//fun main() {
//    val messageDisplay = MessageDisplay(Context())
//    messageDisplay.show("message")
//}

interface MessageDisplay {
    fun show(
        message: String,
        duration: MessageLength = MessageLength.LONG
    )
}


class ToastDisplay(
    val context: Context,
): MessageDisplay {
    override fun show(message: String, duration: MessageLength) {
        val toastDuration = when (duration) {
            MessageLength.SHORT -> Length.LENGTH_LONG
            MessageLength.LONG -> Length.LENGTH_LONG
        }

        Toast.makeText(this.context, message, toastDuration).show()
    }
}