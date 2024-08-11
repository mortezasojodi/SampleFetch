package com.msa.samplefetch.Navigation

import android.os.Bundle
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.toRoute
import com.msa.domain.entity.ProductEntity
import com.msa.samplefetch.Product.ProductDetail.ProductDetailScreen
import com.msa.samplefetch.Product.ProductList.ProductList
import com.msa.samplefetch.Product.ProductList.ProductListViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.serialization.Serializable
import kotlin.reflect.KClass
import kotlin.reflect.full.primaryConstructor

@Composable
fun MainNavHost(
    navController: NavHostController = rememberNavController(),
    innerPadding: PaddingValues
) {

    NavHost(
        navController = navController,
        startDestination = Routes.HomeRoute,
        modifier = Modifier.padding(innerPadding)
    ) {
        composable<Routes.HomeRoute> {
            ProductList(navController)
        }
        composable<Routes.DetailDataClass> {
            val data = it.toRoute<Routes.DetailDataClass>()
            ProductDetailScreen(navController)
        }

    }
}

val bottomRoute = setOf(Routes.SecondRoute, Routes.HomeRoute)


sealed class Routes {

    companion object {
        fun fromRoute(route: String, args: Bundle?): Routes? {
            val subclass = Routes::class.sealedSubclasses.firstOrNull {
                route.contains(it.qualifiedName.toString())
            }
            return subclass?.let { createInstance(it, args) }
        }

        private fun <T : Any> createInstance(kClass: KClass<T>, bundle: Bundle?): T? {
            val constructor = kClass.primaryConstructor
            return constructor?.let {
                val args = it.parameters.associateWith { param ->
                    bundle?.get(param.name)
                }
                it.callBy(args)
            } ?: kClass.objectInstance
        }
    }

    @Serializable
    object HomeRoute : Routes()

    @Serializable
    object SecondRoute : Routes()

    @Serializable
    data class DetailDataClass(
        val id: Int,
        val title: String,
        val price: Float,
        val category: String,
        val description: String,
        val image: String
    ) : Routes() {
        // Secondary constructor that accepts ProductEntity and converts it to DetailDataClass
        constructor(product: ProductEntity) : this(
            id = product.id,
            title = product.title,
            price = product.price,
            category = product.category,
            description = product.description,
            image = product.image
        )

        fun toEntity(): ProductEntity = ProductEntity(
            id = this.id,
            title = this.title,
            price = this.price,
            category = this.category,
            description = this.description,
            image = this.image
        )
    }

}


//@Serializable
//sealed class Screen {
//
//
//    @Serializable
//    data object Home : Screen()
//
//    @Serializable
//    data class Detail(val id: Int) : Screen()
//}