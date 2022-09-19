import {
    createDiv, createI, createListItem, createSpan, createUnorderedList,
    getElements, getElement, isEmpty, shortDate, isNullOrUndefined
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
function getFullName(person) {
    if (isNullOrUndefined(person)) {
        return "N/A";
    }

    return fullName(person.firstName, person.lastName);
}

const CURRENT_WEEK = Math.ceil((Date.now() - new Date(2022, 9, 13).getTime()) / (60 * 60 * 24 * 1000) / 7);

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
    }); console.log(responsable);
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

let outValues = getElements("[data-ruleng]");

/**
 * 
 * @param {string} match 
 * @param {string} string 
 * @param {string} offset 
 * @returns {string}
 */
function upperToHyphenLower(match, string, offset) {
    let prop = match.substring(1).trim();

    if (prop === "CURRENT_WEEK") {
        return CURRENT_WEEK;
    }
    if (prop === "WEEK_END") {
        return WEEK_END;
    }

    return prop;
}

outValues.forEach(val => {
    let content = val.textContent;

    val.textContent = content.replace(/\$\w+/g, upperToHyphenLower);
});