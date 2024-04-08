import { Author } from "./author.interface"
import { Theme } from "./theme.interface"

export interface Article {
    id: number
    title: string
    content: string
    description: string
    publicationDate: Date
    author: Author
    themes: Theme[]
}


export interface CreateArticle {
    themes: number[]
    title: string
    author: string
    content: string
}