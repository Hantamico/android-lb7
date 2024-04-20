import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    private lateinit var editText: EditText
    private lateinit var slider: SeekBar
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Получаем ссылки на компоненты из макета
        editText = findViewById(R.id.edit_text)
        slider = findViewById(R.id.slider)
        textView = findViewById(R.id.text_view)

        // Добавляем слушателя изменений для EditText (ввод суммы счёта)
        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Игнорируем
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                updateTextView()
            }

            override fun afterTextChanged(s: Editable?) {
                // Игнорируем
            }
        })

        // Добавляем слушателя изменений для SeekBar (ползунка)
        slider.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                updateTextView()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // Игнорируем
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // Игнорируем
            }
        })

        // Обновляем результат при запуске активности
        updateTextView()
    }

    private fun updateTextView() {
        val billAmountStr = editText.text.toString()
        val percent = slider.progress
        val formatter = DecimalFormat("#,##0.00")

        if (billAmountStr.isNotEmpty()) {
            val billAmount = billAmountStr.toDouble()
            val taxAmount = (billAmount * percent) / 100.0
            val formattedTaxAmount = formatter.format(taxAmount)

            val resultText = "Сума податку: \$${formattedTaxAmount}"
            textView.text = resultText
        } else {
            textView.text = "" // Очищаем текст, если сумма счета пустая
        }
    }
}