import com.example.userlist.models.Data
import com.example.userlist.models.Meta
import com.google.gson.annotations.SerializedName


data class ApiResponse (

	@SerializedName("code") val code : Int,
	@SerializedName("meta") val meta : Meta,
	@SerializedName("data") val data : List<Data>
)