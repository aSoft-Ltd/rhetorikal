package rhetorikal.witenesses

import kotlinx.css.padding
import kotlinx.css.pct
import react.RBuilder
import reakt.ContainedButton
import reakt.Grid
import rhetorikal.Rhetorician
import styled.css
import styled.styledH3
import styled.styledP

private fun RBuilder.WitnessCard(
    onEdit: (Rhetorician) -> Unit,
    witness: Rhetorician
) = Grid {
    styledH3 {
        +witness.name
    }
    styledP { +"${witness.title} | ${witness.workplace}" }
    ContainedButton("Edit") {
        onEdit(witness)
    }
}

fun RBuilder.WitnessessList(
    onEditWitnessButtonClicked: (Rhetorician) -> Unit,
    onCreateWitnessButtonClicked: () -> Unit,
    data: List<Rhetorician>
) = Grid {
    css { padding(20.pct) }
    Grid {
        ContainedButton("Create A Witness", onClick = onCreateWitnessButtonClicked)
    }
    Grid(cols = "1fr 1fr") {
        for (witness in data) WitnessCard(
            witness = witness,
            onEdit = onEditWitnessButtonClicked
        )
    }
}