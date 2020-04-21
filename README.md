                        
    Proiect PA - 2020 - Bot Sah

Scopul Proiectului este realizarea unui program capabil sa joace sah la un nivel
cat mai bun.

In stadiul actual acesta stie sa foloseasca doar piesele de tip pion.

            
Rulare:
    pentru rulare se va folosi comanda: xboard -fcp "java BoardCenter"
    comanda se va rula din directorul /src din cadrul proiectului
                    
Contributia membrilor:
                    
    Brabete Adrian - Miscarile pionului, alegerea piesei, New Game
                  
    Popescu Bogdan - Interpretarea comenzilor Xboard: White,Black,Force
                  
    Dumitrescu Ioana - ierarhia de clase 
    
Etapa II:
    In stadiul actual programul stie sa mute toate piesele si sa iasa din sah.
Inca nu stie sa castige intentionat, dar stie sa aleaga ultima miscare dintr-o 
lista ce contine toate miscarile posibile, miscare ce are scorul cel mai mare.
Am implementat un algoritm negamax, in care se si verifica daca botul se afla
in sah. De asemenea, stie sa functioneze cu transformarea pionilor in regine.
    Uneori, cand botul se afla in sah (de obicei de o tura) programul nu isi
da seama ca se afla in sah, si returneaza un illegal move (misca alta piesa).

Contributia membrilor:
                
    Brabete Adrian - Mutarile tuturor pieselor, transformare in regine, schimbarea
                
culorii botului
                  
    Popescu Bogdan - Algoritmul Negamax, verificare sah