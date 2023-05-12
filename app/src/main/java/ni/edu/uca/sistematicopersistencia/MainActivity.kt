package ni.edu.uca.sistematicopersistencia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ni.edu.uca.sistematicopersistencia.data.database.dao.ProductoDao
import ni.edu.uca.sistematicopersistencia.data.database.entities.EntityProducto
import ni.edu.uca.sistematicopersistencia.data.database.entities.ProductViewModel
import ni.edu.uca.sistematicopersistencia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val productoViewModel: ProductViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Agregar()
    }

    fun Agregar() {
        binding.btnAdd.setOnClickListener {
            try {
                val nombre = binding.edtNombre.text.toString()
                val precio = binding.edtPrecio.text.toString().toDouble()
                val existencia = binding.edtExistencia.text.toString().toInt()

                val producto =
                    EntityProducto(nombre = nombre, precio = precio, existencia = existencia)

                CoroutineScope(Dispatchers.IO).launch {
                    productoViewModel.insertar(producto)
                }

                Toast.makeText(
                    this,
                    "Se ha registrado el producto exitosamente",
                    Toast.LENGTH_SHORT
                ).show()

            } catch (ex: Exception) {
                Toast.makeText(
                    this, "Error al Insertar: ${ex.toString()}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}