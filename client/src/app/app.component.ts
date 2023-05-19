import { AfterViewInit, Component, ElementRef, ViewChild } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent implements AfterViewInit {
  // ViewChild object is accessible earliest only after AfterViewInit
  @ViewChild('title')
  title!: ElementRef;

  ngAfterViewInit(): void {
    console.info('>>> title: ', this.title.nativeElement.innerText); //  for html header tags, use nativeElement.innerText to access the value
    // for input tags, use nativeElement.value to access the value
    // for div tags, use nativeElement.textContent
  }
}
