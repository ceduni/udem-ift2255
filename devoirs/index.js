import { createDiv, createI, createListItem, createSpan, createUnorderedList, getElement } from 'zenkai'
const DATA_TEAMS = require(`./teams.json`);
const DATA_QUESTIONS = require(`./questions.json`);


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

const CURRENT_WEEK = 2;

let teamsSection = getElement(`[data-display="teams"]`);

let teamsList = createUnorderedList({
    class: ["bare-list", "teams"]
});
DATA_TEAMS.forEach(team => {
    let responsable = team.members.find(e => e.matricule === team.responsable);
    let teamQuestions = DATA_QUESTIONS.find(q => q.team === team.id).questions;

    let item = createListItem({
        class: ["team"]
    });

    let info = createDiv({
        class: ["team-info"]
    });

    let name = createSpan({
        class: ["team-name"]
    }, team.name);

    info.append(name);

    let group = createDiv({
        class: ["team-group"]
    });
    let icon = createI({
        class: ["icon", "icon-person"]
    });
    let groupResponsable = createSpan({
        class: ["team-captain", "fit-content"]
    }, getFullName(responsable));

    group.append(icon, groupResponsable);

    let questions = createUnorderedList({
        class: ["bare-list", "questions"]
    });
    teamQuestions.forEach(tq => {
        let questionItem = createListItem({
            class: ["question"]
        });
        if (tq.used) {
            questionItem.classList.add("used")
        } else if (tq.week < CURRENT_WEEK) {
            questionItem.classList.add("missed")
        }

        questions.append(questionItem);
    })

    item.append(info, group, questions);

    teamsList.append(item);
});

teamsSection.append(teamsList);

let outValue = getElement("[data-value]");
if(outValue.dataset.value === "CURRENT_WEEK") {
    outValue.textContent = CURRENT_WEEK;
}