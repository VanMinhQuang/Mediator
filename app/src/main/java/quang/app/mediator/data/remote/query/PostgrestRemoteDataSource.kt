package quang.app.mediator.data.remote.query

import io.github.jan.supabase.postgrest.query.Columns
import io.github.jan.supabase.postgrest.query.PostgrestRequestBuilder
import quang.app.mediator.core.network.results.APIResult
import quang.app.mediator.core.supabase.SupabaseService
import quang.app.mediator.data.remote.BaseRemote
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostgrestRemoteDataSource @Inject constructor(
     val supabaseService: SupabaseService
): BaseRemote() {
    suspend inline fun <reified T : Any> fetchSingle(
        table: String,
        select: String = "*",
        crossinline filter: PostgrestRequestBuilder.() -> Unit = {}
    ): APIResult<T> {
        return safeCall {
            val response = supabaseService.postgrest.from(table).select(Columns.raw(select)) {
                filter.invoke(this)
            }


            val list = response.decodeAs<List<T>>()

            list.firstOrNull()
                ?: throw Exception("No data found")
        }
    }

    suspend inline fun <reified T : Any> fetchList(
        table: String,
        select: String = "*",
        crossinline filter: PostgrestRequestBuilder.() -> Unit = {}
    ): APIResult<List<T>> {
        return safeCall {
            val response = supabaseService.postgrest.from(table).select(Columns.raw(select)) {
                filter.invoke(this)
            }
            response.decodeList<T>()
        }
    }
}
