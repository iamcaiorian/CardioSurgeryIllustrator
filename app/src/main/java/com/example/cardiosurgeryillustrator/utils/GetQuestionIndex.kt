import java.text.Normalizer
import java.util.Locale

fun getQuestionIndex(response: String): Int {
    val options = listOf(
        "Doenca coronariana (entupimento das arterias do coracao)",
        "Valvopatia mitral (problemas na valvula mitral)",
        "Valvopatia aortica (problemas na valvula aortica)",
        "Valvopatia tricuspide (problemas na valvula tricuspide)",
        "Disseccao aortica",
        "Aneurisma de aorta toracica",
        "Aneurisma de aorta abdominal",
        "Miocardiopatia dilatada ou hipertrofica"
    )

    val normalizedResponse = removeAccents(response)

    return options.indexOf(normalizedResponse)
}

// Função para remover acentos
fun removeAccents(text: String): String {
    return Normalizer.normalize(text, Normalizer.Form.NFD)
        .replace("\\p{M}".toRegex(), "")
        .replace("ç", "c")
        .replace("Ç", "C")
}
