package CoBo.Chatbotfile.Repository.Custom

interface CategoryRepositoryCustom {
    fun saveOrUpdate(category: String)
    fun updateName(oldCategory: String, newCategory: String)
}