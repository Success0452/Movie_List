import com.example.movielist.service.interfaces.ApiService
import javax.inject.Inject
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movielist.models.Results

class MoviePagingSource(private val service: ApiService) : PagingSource<Int, Results>() {
    override fun getRefreshKey(state: PagingState<Int, Results>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Results> {
        return try {
            val prev = params.key?:1
            val response = service.loadMovie(page = prev, size = params.loadSize)
            if (response!!.results.isNotEmpty()){
                val body = response.results
                LoadResult.Page(
                    data = body,
                    prevKey = if (prev==1) null else prev-1,
                    nextKey = if (body.size<params.loadSize) null else prev+1
                )
            }else{
                LoadResult.Error(Exception())
            }
        }catch (e: Exception){
            LoadResult.Error(e)
        }
    }
}