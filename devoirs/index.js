import { 
    createDiv, createI, createListItem, createSpan, createUnorderedList,
    getElements, getElement, isEmpty, shortDate 
} from 'zenkai'
const DATA_TEAMS = require(`./teams.json`);


const { id } = document.body.dataset;

/**
 * Concat of first name and last name
 * @param {string} fName 
 * @param {string} lName 
 * @returns {string}
 */
const fullName = (fName, lName) => fName + " " + lName.toUpperCase();

/**
 * Get the full name
 * @param {*} person 
 * @returns {string}
 */
const getFullName = (person) => fullName(person.firstName, person.lastName);

const CURRENT_WEEK = Math.ceil((Date.now() - new Date(2022, 4, 13).getTime()) / (60 * 60 * 24 * 1000) / 7);

const WEEK_END = shortDate(new Date(new Date(2022, 4, 15).getTime() + 60 * 60 * 24 * 1000 * CURRENT_WEEK * 7));


let teamsSection = getElement(`[data-display="teams"]`);

let teamsList = createUnorderedList({
    class: ["bare-list", "teams"]
});
DATA_TEAMS.forEach(team => {
    let responsable = team.members.find(e => e.matricule === team.responsable);
    const { meetings } = team[id];

    let item = createListItem({
        class: ["team"]
    });

    let info = createDiv({
        class: ["team-info"]
    });

    let name = createSpan({
        class: ["team-name"],
        title: team.name
    }, team.name);

    info.append(name);

    let group = createDiv({
        class: ["team-group"]
    });
    let icon = createI({
        class: ["icon", "icon-person"]
    });
    let groupResponsable = createSpan({
        class: ["team-captain", "fit-content"],
        title: getFullName(responsable)
    }, getFullName(responsable));

    group.append(icon, groupResponsable);

    let questions = createUnorderedList({
        class: ["bare-list", "questions"]
    });

    if (Array.isArray(meetings) && !isEmpty(meetings)) {
        meetings.forEach(qq => {
            let questionItem = createListItem({
                class: ["question"]
            });
            if (qq.used) {
                questionItem.classList.add("used")
            } else if (qq.week < CURRENT_WEEK) {
                questionItem.classList.add("missed")
            }

            questions.append(questionItem);
        })
    }


    item.append(info, group, questions);

    teamsList.append(item);
});

teamsSection.append(teamsList);

let outValues = getElements("[data-value]");

outValues.forEach(val => {
    
if (val.dataset.value === "CURRENT_WEEK") {
    val.textContent = CURRENT_WEEK;
}
if (val.dataset.value === "WEEK_END") {
    val.textContent = WEEK_END;
}
});