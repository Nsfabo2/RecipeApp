package com.example.recipeapp

class Data {
    val data: List<DataDetails>? = null

    class DataDetails {
        var author: String? = null
        var ingredients: String? = null
        var instructions: String? = null
        var title: String? = null

        constructor(author: String?, ingredients: String?, instructions: String?, title: String?) {
            this.author = author
            this.ingredients = ingredients
            this.instructions = instructions
            this.title = title
        }
    }
}