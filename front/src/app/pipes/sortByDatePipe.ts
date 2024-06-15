import { Pipe, PipeTransform } from '@angular/core';
import { Article } from '../interfaces/article.interface';

@Pipe({
  name: 'sortByDatePipe'
})
export class SortByDatePipe implements PipeTransform {

  transform(value: any[] | null, property: string, order: 'asc' | 'desc' = 'asc') {

    if (!value || value.length <= 1) {
      return value;
    }

    return value.sort((a, b) => {
      const dateA = new Date(a[property]).getTime();
      const dateB = new Date(b[property]).getTime();

      if (order === 'asc') {
        return dateA - dateB;
      } else {
        return dateB - dateA;
      }
    });
  }
}
