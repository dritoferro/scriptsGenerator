import java.io.FileOutputStream
import kotlin.io.path.ExperimentalPathApi
import kotlin.io.path.Path
import kotlin.io.path.createTempFile

@ExperimentalPathApi
fun main() {
    println("Olá, bem vindo ao gerador de scripts!")

    val inputFilenames = mutableListOf<String>()
    var filename = ""

    var qtdArquivos = 0

    println("Informe o nome do(s) arquivo(s) na sequencia que devem ser preenchido(s) o(s) campo(s)")
    println("Este(s) arquivo(s) deve(m) estar na pasta resources/input")
    println("Quando não tiver mais arquivos, pressione 0")

    while (filename != "0") {
        println("Arquivo $qtdArquivos:")
        filename = readLine().toString()

        if (filename != "0") {
            inputFilenames.add(filename)
            qtdArquivos++
        }
    }

    println("Informe as palavras chaves para serem usadas na composição do arquivo final separadas por vírgula e sem espaço")
    val headers = readLine().toString().split(",")

    val inputFiles = inputFilenames.map { ClassLoader.getSystemResource("input/$it.txt") }
    val data = inputFiles.map { it.readText(charset("UTF-8")).split("\n") }
    val totalLines = data[0].size

    println("Informe qual a url ou query que deseja montar com os headers no padrão \$header:")
    val base = readLine()

    if (base.isNullOrBlank()) {
        println("A url base ou query não pode ser null")
        return
    }

    val outputBasePath = Path("src/main/resources/output")
    val outputFile = createTempFile(directory = outputBasePath, prefix = "out_", suffix = inputFilenames[0])

    val fileOutputStream = FileOutputStream(outputFile.toFile())

    (0 until totalLines).forEach { line ->
        var message = base!!

        headers.forEachIndexed { index, header ->
            message = message.replace("$$header", data[index][line])
        }

        message += "\n"
        fileOutputStream.write(message.toByteArray())
    }

    fileOutputStream.close()

    println("Seu arquivo está pronto!")

    return
}