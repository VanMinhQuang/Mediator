package quang.app.mediator.core.supabase

import io.github.jan.supabase.annotations.SupabaseInternal
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.postgrest
import quang.app.mediator.BuildConfig
import javax.inject.Inject

class SupabaseService @Inject constructor() {

    @OptIn(SupabaseInternal::class)
    private val client = createSupabaseClient(
        supabaseKey = BuildConfig.SUPABASE_KEY,
        supabaseUrl = BuildConfig.SUPABASE_URL,


    ){
        install(Auth)
        install(Postgrest)
    }

    val auth: Auth get() = client.auth
    val postgrest: Postgrest get() = client.postgrest





    /** ----------------------- PostgREST Operations ----------------------- */
//    suspend inline fun <reified T> fetch(
//        table: String,
//        select: String = "*",
//        noinline filter: (PostgrestRequestBuilder.() -> Unit)? = null
//    ): APIResult<T> {
//        return try {
//            withTimeout(defaultTimeout) {
//                val response = postgrest.from(table).select(Columns.raw(select)) {
//                    filter?.invoke(this)
//                }
//
//                // Decode dynamically
//                val data: T = if (T::class == List::class) {
//                    // If T is a List, decode as a list
//                    @Suppress("UNCHECKED_CAST")
//                    response.decodeList<Any>() as T
//                } else {
//                    // Otherwise decode as single object
//                    response.decodeAs<T>()
//                }
//
//                if (data != null) {
//                    APIResult.Success(data)
//                } else {
//                    APIResult.Error(NetworkError.Unknown("No data found"))
//                }
//            }
//        } catch (e: Exception) {
//            handleError(e)
//        }
//    }



}
