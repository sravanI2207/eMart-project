import { SubCategory } from './sub-category';

export interface Item{
    id:number,
    name: string
    categoryId: number
    subcategoryId: number,
    price: number,
    description: string,
    stock: number,
    remarks: string,
    img: string
}