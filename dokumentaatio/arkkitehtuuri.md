
# Arkkitehtuurikuvaus

## Rakenne

Pakkaus _ajankayttosovellus.ui_ sisältää käyttöliittymän, joka on toteutettu JavaFX:llä. Sovelluslogiikka löytyy pakauksesta _ajankayttosovellus.domain_.  

![Pakkausrakenne](https://github.com/eevib/ot-harjoitustyo/blob/master/dokumentaatio/images/pakkausrakenne.jpg)

## Käyttöliittymä

Käyttöliittymä sisältää toistaiseksi vain yhden näkymän, josta löytyy kaikki toiminnot. Käyttöliittymä löytyy luokasta _ajankayttosovellus.CalenderUi_.  

## Sovelluslogiikka

Käyttöliittymä on yhteydessä luokkaan _CalenderService_, joka hoitaa sovelluslogiikan. 

![Luokka/pakkauskaavio](https://github.com/eevib/ot-harjoitustyo/blob/master/dokumentaatio/images/luokkakaavio.png)

## Päätoiminnallisuudet

### Todon lisääminen ja ajastaminen

Käyttäjä lisää todoon kirjoittamalla kenttään todon nimen ja painamalla nappia "Add todo". Tapahtumakäsittelijä kutsuu sovelluslogiikan _calenderService_ metodia addTodo() ja antaa parametriksi todon nimen. TodoService hakee luokasta Calender seuraavan todo id:een ja luo uuden todoon annetulla nimellä ja lisää sen Calender luokan todo listaan. Tämän jälkeen tapahtumakäsittelijä näyttää käyttäjälle tekstiä "Todo was added, add another one."   

![Sekvenssikaavio](https://github.com/eevib/ot-harjoitustyo/blob/master/dokumentaatio/images/add_todo.png)

Tämän jälkeen käyttäjä voi ajastaa todon painamalla nappia "Schedule todo", käyttäjä täyttää kenttiin tarvittavat tiedot ja painaa "Add timing". 
