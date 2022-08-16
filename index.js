import { getElement, Collapsible, getTemplate, isNullOrWhitespace, hasOwn, htmlToElement } from 'zenkai'
// import './assets/css/normalize.css';
// import './assets/css/base.css';
// import './assets/css/style.css';

import data from './data/teachers.yml';


let body = getElement('.body-content');

let teachers = getElement(`[data-name="teachers"]`);

if(teachers) {
    let tplTeacher = getTemplate("#tpl-teacher");
    let content = tplTeacher.innerHTML;
    
    data.forEach(teacher => {
        let tplElement = htmlToElement(parse(content, teacher));
        teachers.append(tplElement);
    });
}

Collapsible(body);

function parse(content, data) {
    var result = content.trim();

    let matches = result.match(/(#[A-Za-z0-9_]+)/g)

    matches.forEach((key, i) => {
        let attr = key.substring(1);
        if (hasOwn(data, attr)) {
            result = result.replace(key, data[attr]);
        }
    });

    return result;
}