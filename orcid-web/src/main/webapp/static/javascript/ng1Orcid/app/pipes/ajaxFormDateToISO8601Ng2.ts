declare var Number: any;

import { Injectable, Pipe, PipeTransform } 
    from '@angular/core';

@Pipe({
    name: "ajaxFormDateToISO8601"
})

@Injectable()
export class AjaxFormDateToISO8601Pipe implements PipeTransform {
    transform(input: any): string {
        if (typeof input != 'undefined'){
            var str = '';
            if (input.year) str += input.year;
            if (input.month) {
                if (str.length > 0) str += '-';
                str += Number(input.month).pad(2);
            }
            if (input.day) {
                if (str.length > 0)
                    str += '-';
                str += Number(input.day).pad(2);
            }
            return str;
        } else {
            return '';
        }
    }
}