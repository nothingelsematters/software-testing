const pageUrl = 'http://localhost:3000/';

describe('cypress tests', () => {
    it(`home page`, async () => {
        cy.visit(pageUrl);
        cy.contains('Home page').should('be.visible')
    });

    it('authorization wrong success', checkAuthorization('user', 'password', 'OK'));

    it('authorization wrong password', checkAuthorization('user', 'wrongPassword', 'Unauthorized'));

    it('authorization wrong user', checkAuthorization('wrongUser', 'wrongPassword', 'Bad Request'));

    function checkAuthorization(login, password, result) {
        return async () => {
            cy.visit(pageUrl + 'login');
            cy.get('input#login').type(login);
            cy.get('input#password').type(password);
            cy.get('#button').click();
            cy.wait(100);
            cy.contains(result).should('be.visible')
        }
    }

    it('about page', async () => {
        cy.visit(pageUrl + 'about');
        cy.contains('About Page').should('be.visible')
    });
});
