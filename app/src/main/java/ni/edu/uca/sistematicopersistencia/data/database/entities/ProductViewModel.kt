package ni.edu.uca.sistematicopersistencia.data.database.entities

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ni.edu.uca.sistematicopersistencia.data.database.BaseDatos
import ni.edu.uca.sistematicopersistencia.data.database.dao.ProductoDao

class ProductViewModel (application: Application) : AndroidViewModel(application) {

    private val productoDao: ProductoDao

    init {
        val database = BaseDatos.obtBaseDatos(application.applicationContext)
        productoDao = database.productoDao()
    }


    suspend fun insertar(entityProducto: EntityProducto) = withContext(Dispatchers.IO) {
        productoDao.insertarReg(entityProducto)
    }
}