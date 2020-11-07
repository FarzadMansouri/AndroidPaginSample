package ir.frzd.paging_kotlin_mvvm.model

data class  MovieModel(
    val page: Int,
    val results: List<ResultModel>,
    val total_pages: Int,
    val total_results: Int
)