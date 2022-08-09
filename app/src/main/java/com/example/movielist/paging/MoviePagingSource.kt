import com.example.movielist.service.interfaces.ApiService
import javax.inject.Inject
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movielist.models.Results

// Movie Paging Source class which extends PagingSource interface to handle data loading
class MoviePagingSource(private val service: ApiService) : PagingSource<Int, Results>() {

    // override getRefreshKey to implement the function
    override fun getRefreshKey(state: PagingState<Int, Results>): Int? {
        return state.anchorPosition
    }

    // override load to implement the function
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Results> {
        // use of try/catch to ensure error handling
        return try {
            // declaring prevKey
            val prev = params.key?:1
            // making request to the server
            val response = service.loadMovie(page = prev, size = params.loadSize)
            // checking response from the request
            if (response!!.results.isNotEmpty()){
                // assign response body variable
                val body = response.results
                // working on the response
                LoadResult.Page(
                    data = body,
                    prevKey = if (prev==1) null else prev-1,
                    nextKey = if (body.size<params.loadSize) null else prev+1
                )
            }else{
                // handling error from the response
                LoadResult.Error(Exception())
            }
            // handling error from the response
        }catch (e: Exception){
            LoadResult.Error(e)
        }
    }
}