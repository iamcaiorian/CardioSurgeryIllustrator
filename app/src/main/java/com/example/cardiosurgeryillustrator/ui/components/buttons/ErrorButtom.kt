import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ErrorButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = androidx.compose.material3.MaterialTheme.colorScheme.error
        ),
        shape = RoundedCornerShape(topEnd = 0.dp, bottomEnd = 0.dp, topStart = 32.dp, bottomStart = 32.dp),
        modifier = modifier
            .padding(8.dp)
            .height(60.dp)
            .width(160.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Close,
            contentDescription = null,
            modifier = Modifier.padding(end = 8.dp)
        )
        Text(text = text)
    }
}
@Preview
@Composable
fun ErrorButtonPreview() {
    ErrorButton(
        text = "Errado",
        onClick = {})
}