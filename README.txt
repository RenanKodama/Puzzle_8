-------------------Trabalho de Inteligência Artificial-------------------

Alunos:
Mateus Tomoo Yonemoto Peixoto(1602055);
Renan Kodama Rodrigues(1602098);

Iniciando Prjeto Puzzle_8
	1- Abrir o projeto em uma IDE com suporte ao JAVA (netbeans ou eclipse);
	2- Montar o projeto, e logo abaixo pelo console repare que o programa solicita o caminho de entrada;
		2.1- Para uma entrada pradrão digite: "teste1.txt", "teste2.txt", "teste3.txt" ou "teste4.txt"
		2.2- Para uma entrada especifica cria-se um arquivo ".txt" no diretorio "Puzzle_8/"
	3- Informe qual Heuristica usar  ManHattan[M-m]|Camberra[C-c];
	5- E logo após será mostrado os resultados no console junto com a analise do algoritmo;

OBS:
	Os aquivos com nome de "teste*.txt" são os arquivos de configuração inicial para o puzzle, contendo 
	tambem um arquivo chamado "obejtivo.txt" que nos informa a configuração final que o tabuleiro deve ter
	para ser aceito, para os arquivos de entrada especificos o usuario deverá crialos no formato de uma 
	matriz 3x3 com valores de 0 à 8 onde o simbolo "0" representa o espaço em branco no puzzle_8, exemplo:
		1,2,3
		4,5,6
		7,8,0
    Para o arquivo de entrada "teste04.txt" não há soluão, o algoritmo irá ficar em loop;
	O arquivo "objetivo.txt" pode ser alterado também, mas por padrão adotamos que o estado objetibo segue 
	o mesmo exemplo citado acima.
