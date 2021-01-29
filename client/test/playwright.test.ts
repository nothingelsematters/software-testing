const { chromium } = require('playwright');

const pageUrl = 'http://localhost:3000/';
const delay = (ms) => new Promise(res => setTimeout(res, ms));

describe('Playwright tests', function () {
    let browser;
    let page;

    beforeEach(async () => {
        browser = await chromium.launch();
        page = await browser.newPage();
    });

    // afterEach(async () => {
    //     await browser.close();
    // });

    it('home page', async () => {
        await page.goto(pageUrl);
        expect(await page.innerText('.title')).toBe('Home page');
    });

    it('authorization wrong success', checkAuthorization('user', 'password', 'OK'));

    it('authorization wrong password', checkAuthorization('user', 'wrongPassword', 'Unauthorized'));

    it('authorization wrong user', checkAuthorization('wrongUser', 'wrongPassword', 'Bad Request'));

    function checkAuthorization(login, password, result) {
        return async () => {
            await page.goto(pageUrl + 'login');
            await page.fill('#login', login);
            await page.fill('#password', password);
            await page.click('.button');
            await delay(100);
            expect(await page.innerText('.result')).toBe(result);
        }
    }

    it('about page', async () => {
        await page.goto(pageUrl + 'about');
        expect(await page.innerText('.title')).toBe('About Page');
    });
});
