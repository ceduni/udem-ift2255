import { getElement, getElements, Collapsible, getTemplate, hasOwn, htmlToElement } from 'zenkai'
// import './assets/css/normalize.css';
// import './assets/css/base.css';
// import './assets/css/style.css';

import TEACHERS_DATA from './data/teachers.yml';
import WEEK_DATA from './data/weeks.yml';


let body = getElement('.body-content');

let teachers = getElement(`[data-name="teachers"]`);

let schedule = getElements(`[data-name="schedule"]`);

let weeks = getElement(`[data-name="weeks"]`);

if (teachers) {
    let tplTeacher = getTemplate("#tpl-teacher");
    let content = tplTeacher.innerHTML;

    TEACHERS_DATA.forEach(teacher => {
        let tplElement = htmlToElement(parse(content, teacher));
        teachers.append(tplElement);
    });
}

if (weeks) {
    let tplWeek = getTemplate("#tpl-week");
    let content = tplWeek.innerHTML;

    WEEK_DATA.forEach(data => {
        let tplElement = htmlToElement(parse(content, data));
        let items = getElements(`[data-item]`, tplElement);
        
        items.forEach(it => {
            const { item, bind } = it.dataset;

            let bindData = data[bind];
            let tplWeek = getTemplate(`#${item}`);
            let content = tplWeek.innerHTML;

            bindData.forEach(bd => {
                let tplWeekElement = htmlToElement(parse(content, bd))
                it.append(tplWeekElement);
            })

        })
        weeks.append(tplElement);
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