# Capitolo 1 - Analisi
## 1.1 Requisiti
Il software vuole riprendere il celebre videogioco 2d platform Super Sonic 1991. Il giocatore controlla Sonic, che dovrà superare nemici e ostacoli per arrivare alla fine del livello, raccogliendo più anelli possibili, e sfruttando diversi power-up che potenziano Sonic.
### Requisiti funzionali
!!
- Il player deve:
    - poter muoversi verso destra, verso sinistra con velocità incrementale, e saltare
    - collezionare monete e ottenere power-ups che ne potenziano le abilità di gioco
    - sconfiggere i nemici attraverso il salto
- I nemici devono:
    - ostacolare il player facendogli perdere vita al contatto
    - potersi muovere verso destra e verso sinistra con una loro logica
- 
### Requisiti non funzionali
!!
- Menù iniziale
- Il gameplay dovrà risultare fluido
- Il videogioco dovrà essere compatibile con la maggiorparte dei dispositivi e sistemi operativi
## 1.2 Analisi e modello del dominio
Il giocatore ha modo di muoversi all'interno di una mappa, la quale si compone di blocchi non compenetrabili, trappole, nemici e oggetti raccoglibili.
Sonic può collezionare monete per incrementare il punteggio della partita, raccogliere power-ups per potenziarsi e sconfiggere nemici tramite l'abilità di salto.
Insieme a Sonic, si muovono nella mappa i nemici, seguendo una logica prestabilita. Quando un nemico colpisce il personaggio, quest'ultimo subisce danno, decrementando la propria salute.
Nella mappa sono sparse delle trappole, che al contatto con Sonic, gli provocano qualche tipo di effetto, per esempio un danno oppure un effetto di qualche tipo.



