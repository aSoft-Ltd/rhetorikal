package rhetorikal.witenesses

import kotlinx.css.JustifyContent
import kotlinx.css.justifyContent
import kotlinx.css.padding
import kotlinx.css.pct
import kotlinx.extensions.text
import react.RBuilder
import reakt.ContainedButton
import reakt.FlexBox
import reakt.Form
import reakt.TextInput
import rhetorikal.Rhetorician
import rhetorikal.RhetoricianParams
import styled.css

fun RBuilder.WitnessForm(
    onWitnessCreated: (RhetoricianParams) -> Unit,
    onWitnessEdited: (Rhetorician) -> Unit,
    witness: Rhetorician?
) = Form {
    css {
        padding(20.pct)
    }

    TextInput(
        name = "name",
        label = "Name",
        value = witness?.name
    )

    TextInput(
        name = "title",
        label = "Job Title",
        value = witness?.title
    )

    TextInput(
        name = "workplace",
        label = "Company",
        value = witness?.workplace
    )

    FlexBox {
        css {
            justifyContent = JustifyContent.flexEnd
        }
        ContainedButton(name = "Submit")
    }
} onSubmit {
    val name by text()
    val title by text()
    val workplace by text()
    if (witness == null) {
        val params = RhetoricianParams(name, title, workplace)
        onWitnessCreated(params)
    } else {
        val edit = witness.copy(
            name = name, title = title, workplace = workplace
        )
        onWitnessEdited(edit)
    }
}