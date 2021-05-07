package rhetorikal

import kotlinx.browser.document
import logging.ConsoleAppender
import logging.Logging
import reakt.setContent

fun main() = document.getElementById("root").setContent {
    Logging.init(ConsoleAppender())
    RhetorikalContainer()
}