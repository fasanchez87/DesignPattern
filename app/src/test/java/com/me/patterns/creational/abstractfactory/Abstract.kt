package com.me.patterns.creational.abstractfactory

import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.Test

//This is Abstract Factory method.
//

enum class DataSourceType(
    val type: String,
    val message: String
) {
    LOCAL(
        "LOCAL",
        "Get data from local source"
    ),
    REMOTE(
        "REMOTE",
        "Get data from remote source"
    );

    companion object {
        fun getDataSourceType(type: String) = entries.firstOrNull { it.type == type } ?: throw IllegalArgumentException("Data source type not found")
    }
}


interface DataSource {

    fun getData(): String
}

class LocalDataSource: DataSource {

    override fun getData(): String = DataSourceType.LOCAL.message

}

class RemoteDataSource: DataSource {

    override fun getData(): String = DataSourceType.REMOTE.message
}

//class FileStorage: DataSource

abstract class DataSourceFactory {

    abstract fun makeDataSource(): DataSource

    companion object {

        //*inline: funcion *inline le dice al compilador que copie el contenido de la funcion en donde se llama
        //en lugar de llamar a la función como tal; esto mejora la eficiencia en tiempo de ejecución debido a que evita
        //la sobrecarga de llamadas a la función.

        //*reified: reified se usa en funciones de *tipos genericas, para acceder al valor real del objeto generico en este caso
        //necesitamos acceder al valor real de DataSource; cuando el app esta en ejecucion y como estamos estamos usando la reflexión
        //ya que estamos determinando que clase llamar cuando se crea la factory, si marcamos con reified el compilador en tiempo de ejecucion
        //me conserva la informacion del tipo en tiempo de ejecucion;

        inline fun <reified T : DataSource> createFactory(): DataSourceFactory {

            return when (T::class) {

                LocalDataSource::class -> {
                    LocalFactory()
                }

                RemoteDataSource::class -> {
                    RemoteFactory()
                }

                else -> {
                    throw IllegalArgumentException()
                }
            }
        }
    }
}

class LocalFactory: DataSourceFactory() {

    override fun makeDataSource(): DataSource {
        return LocalDataSource()
    }
}

class RemoteFactory: DataSourceFactory(){

    override fun makeDataSource(): DataSource {
        return RemoteDataSource()
    }

}

class Repository {

    fun getDataFromLocal() = DataSourceFactory.createFactory<LocalDataSource>()

    fun getDataFromRemote() = DataSourceFactory.createFactory<RemoteDataSource>()

}

class FactoryTest {

    @Test
    fun whenGiveLocalDataSourceThenReturnDataFromLocalSource(){
        val repository = Repository()
        val data = repository.getDataFromLocal().makeDataSource().getData()
        assertEquals(data, DataSourceType.LOCAL.message)
    }

    @Test
    fun whenGiveRemoteDataSourceThenReturnDataFromRemoteSource(){
        val repository = Repository()
        val data = repository.getDataFromRemote().makeDataSource().getData()
        assertEquals(data, DataSourceType.REMOTE.message)
    }

    @Test
    fun whenGiveDataSourceThenVerifyIfDataSourceIsInstanceOfLocalSource(){
        val repository = Repository()
        val dataSource = repository.getDataFromLocal().makeDataSource()
        assertTrue(dataSource is LocalDataSource)
    }

    @Test
    fun whenGiveDataSourceThenVerifyIfDataSourceIsInstanceOfRemoteSource(){
        val repository = Repository()
        val dataSource = repository.getDataFromRemote().makeDataSource()
        assertTrue(dataSource is RemoteDataSource)
    }
}





