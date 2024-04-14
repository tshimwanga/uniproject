from connexion import *
import random

from blessed import Terminal

term = Terminal()
# definition de la couleur du tableau
print(term.home + term.clear + term.hide_cursor)

print(term.bright_blue(" "))
print(term.pink + term.on_black + ' ')


def get_data_structure(filename):
    """Give the data structure of game
    parameters
    __________
    filename:map of game
    
    return
    ______
    board: dictionary of team1, team2 and food (dict)
    size:size of board(tuple)
    version
    _______
    specification: Ménie Ngongo (v.1 05/03/2022)
    implementation: Agathe Mekoui(v.1 05/03/2022)
    """
    board = {}
    foods = ["apples", "berries", "mice", "rabbits", "deers"]

    # incrementation pour les differents  loups normaux
    nb_basic_wolves_team1 = 1
    nb_basic_wolves_team2 = 1
    # lecture d'un fichier
    file = open(filename, "r")
    line = file.readline()
    while line:

        if "alpha" in line:
            infos = line.split(" ")
            if int(infos[0]) == 1:
                board[(int(infos[1]), int(infos[2]))] = ['alpha1', 100]
            else:
                board[(int(infos[1]), int(infos[2]))] = ['alpha2', 100]
        if "omega" in line:
            infos = line.split(" ")
            if int(infos[0]) == 1:
                board[(int(infos[1]), int(infos[2]))] = ['omega1', 100]
            else:
                board[(int(infos[1]), int(infos[2]))] = ['omega2', 100]

        if "normal" in line:
            infos = line.split(" ")
            if int(infos[0]) == 1:
                board[(int(infos[1]), int(infos[2]))] = ['normal1', 100]
            else:
                board[(int(infos[1]), int(infos[2]))] = ['normal2', 100]

        if any(food in line for food in foods):
            infos = line.split(" ")
            board[(int(infos[0]), int(infos[1]))] = [infos[2], (int(infos[3]))]

        if len(line.split(" ")) == 3:
            size = (int((line.split(" "))[0]), int((line.split(" "))[1]))

        line = file.readline()
    file.close()

    return board, size


# print(get_data_structure('README_gr_xx.txt')[0])
# print(get_data_structure('README_gr_xx.txt'))


def compute_distance(r1, c1, r2, c2):
    """A function to computer the distance between between to cases

    parameters
    __________
    r1: cordinate x of first position(int)
    c1: cordinate y of first position(int)
    r2: cordinate x of second position(int)
    c2: cordinate y of second position(int)

    Returns:
    ________
    max: distance between two cordinates(int)
    version:
    _______
    specification: Agathe Mekoui(v.1 12/03/2022)
    implementation: Agathe Mekoui(v.1 12/03/2022)

    """
    # le max(|r2 - r1|, |c2 - c2|)

    r2_r1 = abs(r2 - r1)
    c2_c2 = abs(c2 - c1)

    return max(r2_r1, c2_c2)


def pacification(omega_position, board):
    """Calm the game and stop the attack
    
    Parameters:
    -----------
    omega_position: The coordinates of Omega (tuple)
    board:data structure of game(dict)
    
    Returns:
    _______
    no_attack: list of wolf will not attack(list)
    
    Version:
    -------
    specification: Ritaz Mhia(v.1 18/02/2022 -->v.2 15/03/2022) 
    Implementation: Ritaz Mhia(v.1 15/03/2022)
    
    """
    no_attack = []
    if omega_position in board:
        if board[omega_position][1] >= 40:
            if (board[omega_position][0]).startswith("omega"):
                for element in board.keys():
                    if compute_distance(omega_position[0], omega_position[1], element[0], element[1]) in range(1, 7):
                        if (board[element][0]).startswith("normal") or (board[element][0]).startswith("alpha"):
                            no_attack.append(element)

                board[omega_position][1] -= 40
    return no_attack


def move_wolf(wolf_old_position, wolf_new_position, board, board_size):
    """Move the wolves in different places

    parameters: 
    ----------
    wolf_old_position : the coordinates of the wolf (tuple)
    wolf_new_position: New cordinates of the wolf (tuple)
    board:data structure of game(dict)
    board_size: size of board(tuple)

    Version
    -------
    specification :  Menie Ngongo Tshimwanga (v.1 18/02/2022 --> v.2 17/03/2022)
    implementation: Ritaz Mhia (v.1 17/03/2022)
    """
    if wolf_old_position in board and wolf_new_position not in board:
        if wolf_new_position[0] <= board_size[0] and wolf_new_position[1] <= board_size[1]:
            if compute_distance(wolf_old_position[0], wolf_old_position[1], wolf_new_position[0],
                                wolf_new_position[1]) == 1:
                board[wolf_new_position] = board[wolf_old_position]
                del board[wolf_old_position]

    # print(board)


def feed(wolf_position, food_position, board):
    """Give the food to the wolves and increase their energies
    Parameters:
    -----------
    wolf_position: the coordinates of the wolf (tuple)
    food_position: the coordinates of the food (tuple)
    board:data structure of game(dict)
    
    Version:
    --------
    specification: Menie Ngongo Tshimwanga (v.1 18/02/2022 --> v.2 14/03/2022)
    implementation: Menie Ngongo Tshimwanga (v.1 14/03/2022)
    
    """

    if wolf_position in board and food_position in board:
        if compute_distance(wolf_position[0], wolf_position[1], food_position[0], food_position[1]) <= 1:
            wolf_energy = board[wolf_position][1]
            food_energy = board[food_position][1]
            if wolf_energy < 100 and food_energy != 0:
                mq = 100 - wolf_energy
                if mq >= food_energy:
                    new_wolf_energy = wolf_energy + food_energy
                    new_food_energy = 0
                    board[wolf_position][1] = new_wolf_energy
                    board[food_position][1] = 0
                else:
                    new_food_energy = food_energy - mq
                    new_wolf_energy = wolf_energy + mq
                    board[wolf_position][1] = new_wolf_energy
                    board[food_position][1] = new_food_energy
                    if new_food_energy <= 0:
                        del board[food_position]


def bonus(wolf_receiver_position, board, equipe):
    """"Give the bonus to wolf
    parameters
    __________
    wolf_receiver_position:the cordinate of alpha(tuple)
    board:data structure of game(dict)
    equipe: number of team(int)
    return:
    _______
    Bonuus:supplement energy(int)
    
    version
    ______
    specification: Menie Ngongo Tshimwanga (v.1 17/03/2022)
    implementation: Menie Ngongo Tshimwanga (v.1 17/03/2022)
    
    """
    bonuus = 0
    if equipe == 1:

        if wolf_receiver_position in board.keys():
            for wolf in board.keys():
                if compute_distance(wolf_receiver_position[0], wolf_receiver_position[1], wolf[0], wolf[1]) in range(1,
                                                                                                                     3) and \
                        board[wolf][0] != 'alpha1':
                    bonuus += 10
                if compute_distance(wolf_receiver_position[0], wolf_receiver_position[1], wolf[0], wolf[1]) in range(1,
                                                                                                                     5) and \
                        board[wolf][0] == 'alpha1':
                    bonuus += 30
    elif equipe == 2:
        if wolf_receiver_position in board.keys():
            for wolf in board.keys():
                if compute_distance(wolf_receiver_position[0], wolf_receiver_position[1], wolf[0], wolf[1]) in range(1,
                                                                                                                     3) and \
                        board[wolf][0] != 'alpha2':
                    bonuus += 10
                if compute_distance(wolf_receiver_position[0], wolf_receiver_position[1], wolf[0], wolf[1]) in range(1,
                                                                                                                     5) and \
                        board[wolf][0] == 'alpha2':
                    bonuus += 30

    return bonuus


def attack(wolf_attaker_position, wolf_attacked_position, board, no_attack):
    """ This function allows to launch attacks
    
    Parameters:
    ----------
    wolf_attaker_position : the coordinates of the wolf from team 1 (tuple)
    wolf_attacked_position : the coordinates of the wolf from team 2 (tuple)
    board: data structure of game(dict)
    no_attack: list of pacified wolf(list)
    Version:
    -------
    specification : Menie Ngongo tshimwanga (v.1 18/02/2022--> v.2 17/03/2022)
    implementation: Menie Ngongo tshimwanga (v.1 17/03/2022)

    """

    if wolf_attaker_position in board.keys() and wolf_attacked_position in board.keys():
        if wolf_attaker_position not in no_attack:
            if board[wolf_attaker_position][1] != 0 and board[wolf_attacked_position][1] != 0:
                if compute_distance(wolf_attaker_position[0], wolf_attaker_position[1], wolf_attacked_position[0],
                                    wolf_attacked_position[1]) == 1:
                    impact = round((board[wolf_attaker_position][1] + bonus(wolf_attaker_position, board, int(
                        board[wolf_attaker_position][0][-1]))) / 10)
                    new_energy = board[wolf_attacked_position][1] - impact
                    if new_energy < 0:
                        new_energy = 0

                    board[wolf_attacked_position][1] = new_energy


def get_emoji(name, energy):
    """"give the symbole for each wolf and food
    parameters
    _________
    name: name of each symbole(str)
    energy: the energy of the entity
    return
    ______
    symbole:(str)
    
    version
    _______
    specification: Agathe Mekoui(v.1 12/03/2022)
    implementation: Agathe Mekoui(v.1 12/03/2022)
    """
    if energy <= 0:
        return "  "
    elif name.startswith('normal'):
        return "🐺"
    elif name.startswith('alpha'):
        return "🦊"
    elif name.startswith('omega'):
        return "🐱"
    elif name == 'berries':
        return "🍒"
    elif name == 'deers':
        return "🦌"
    elif name == 'apples':
        return "🍎"
    elif name == 'mice':
        return "🐁"
    elif name == 'rabbits':
        return "🐇"

foods = ['berries', 'deers', 'apples', 'mice', 'rabbits']
def display_board(board_dict):
    """Print the board of game
    parameters
    __________
    board_dict: data structure of game(dict)
    
    version
    _______
    specification: Agathe Mekoui(v.1 12/03/2022)
    implementation: Agathe Mekoui(v.1 12/03/2022-->v.2 26/03/2022)
    """

    cl = '|'
    rw = '__'

    ligne = 20
    colonne = 20
    board = ""
    line = "======" * colonne
    # board = ligne*rw + '\n'
    board += line + "\n"
    for i in range(1, ligne + 1):
        board += "|"
        for j in range(1, colonne + 1):
            if (i, j) in board_dict:
                board += " " + get_emoji(board_dict[(i, j)][0], board_dict[(i, j)][1]) + " "  # Replace by emoji
            else:
                board += '    '

            board += '||'
        board += "\n" + line + "\n"

    print(board)
    t = 1
    for s in board_dict:
        #if board_dict[s][0] not in ('berries', 'mice', 'apples', 'deers', 'rabbits'):
        print(term.move_yx(t, 130) + "energy" + ' ' + board_dict[s][0] + ' ' + str(board_dict[s][1]))
        t += 1
    print('\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n')


display_board(get_data_structure('README_gr_xx.txt')[0])


def get_orders(orders: str):
    """take the orders of game
    parameters
    _________
    
    orders:different orders of game(str)
    return
    ______
    list_paci, list_food, list_attack, list_move: list of each different type of orders(list)
    
    version
    _______
    specification : Menie Ngongo tshimwanga (v.1 18/02/2022--> v.2 14/03/2022)
    implementation: Menie Ngongo tshimwanga (v.1 14/03/2022)

    
    """
    # if board[board.keys()][0].startswith('alpha') and board[board.keys()][1]!=0:
    list_paci = []
    list_food = []
    list_attack = []
    list_move = []
    orders.split(" ")
    for element in orders.split(" "):
        if 'pacify' in element:
            list_paci.append(element)

        elif '<' in element:
            list_food.append(element)

        elif '*' in element:
            list_attack.append(element)
        elif '@' in element:
            list_move.append(element)
    return list_paci, list_food, list_attack, list_move



def get_AI_orders(board, size, team_number):
    """Orders come from IA
    parameters
    __________
    board:data structure of game(dict)
    size: size of board(tuple)
    team_number: number of each teams(int)
    return
    ______
    orders: orders random of AI(str)
    version
    _______
    specification: Menie Ngongo Tshimwanga (v.1 14/03/2022)
    implementation: Agath Mekoui, Ritaz Mhia, Menie Ngongo Tshimwanga (v.1 14/03/2022--> v.2 24/03/2022--> v.3 27/04/2022)
    """
    # se deplacer en meute
    # on determine si qui est le plus proche entre la nourriture et le le loup adversaire
    
    orders = []
    wolves = [x for x in board if board[x][0].endswith(str(team_number))]
    print(wolves)
    directions = {'up': (-1,0), 'down': (1, 0), 'right': (0, 1), 'left': (0, -1) }

    team_1_direction = (1, 1)
    team_2_direction = (-1, -1)
    direction = team_1_direction if team_number == 1 else team_2_direction
    ennemy_number = "1" if team_number == 2 else "2"
    
    
    # check if there is food
    for x in wolves:
        #verifie si la position du loup est et reste dans le tableau
        if size[0] > x[0] > 1 and x[1] < size[1] and x[1] > 1:
            if (x[0]-1, x[1]) in board and board[(x[0]-1, x[1])][0] in foods:
                orders.append("{}-{}:<{}-{}".format(x[0], x[1], x[0]-1, x[1]))
            if (x[0], x[1]+1) in board and board[(x[0], x[1]+1)][0] in foods:
                orders.append("{}-{}:<{}-{}".format(x[0], x[1], x[0], x[1] +1))
            if (x[0]+1, x[1]) in board and board[(x[0]+1, x[1])][0] in foods:
                orders.append("{}-{}:<{}-{}".format(x[0]+1, x[1], x[0], x[1]))
            if (x[0], x[1]-1) in board and board[(x[0], x[1]-1)][0] in foods:
                orders.append("{}-{}:<{}-{}".format(x[0], x[1], x[0], x[1] - 1))
            if (x[0]-1, x[1]-1) in board and board[(x[0]-1, x[1]-1)][0] in foods:
                orders.append("{}-{}:<{}-{}".format(x[0], x[1], x[0]-1, x[1]-1))
            if (x[0]-1, x[1]+1) in board and board[(x[0]-1, x[1]+1)][0] in foods:
                orders.append("{}-{}:<{}-{}".format(x[0], x[1], x[0]-1, x[1] +1))
            if (x[0]+1, x[1]-1) in board and board[(x[0]+1, x[1]-1)][0] in foods:
                orders.append("{}-{}:<{}-{}".format(x[0], x[1], x[0]+1, x[1]-1))
            if (x[0]+1, x[1]+1) in board and board[(x[0]+1, x[1]+1)][0] in foods:
                orders.append("{}-{}:<{}-{}".format(x[0], x[1], x[0]+1, x[1] +1))

    # attack ennemies
    for x in wolves:
        if size[0] > x[0] > 1 and size[1] > x[1] > 1:
            if (x[0]-1, x[1]) in board and board[(x[0]-1, x[1])][0].endswith(ennemy_number):
                orders.append("{}-{}:*{}-{}".format(x[0], x[1], x[0]-1, x[1]))
            if (x[0], x[1]+1) in board and board[(x[0], x[1]+1)][0].endswith(ennemy_number):
                orders.append("{}-{}:*{}-{}".format(x[0], x[1], x[0], x[1] +1))
            if (x[0]+1, x[1]) in board and board[(x[0]+1, x[1])][0].endswith(ennemy_number):
                orders.append("{}-{}:*{}-{}".format(x[0]+1, x[1], x[0], x[1]))
            if (x[0], x[1]-1) in board and board[(x[0], x[1]-1)][0].endswith(ennemy_number):
                orders.append("{}-{}:*{}-{}".format(x[0], x[1], x[0], x[1] - 1))
            if (x[0]-1, x[1]-1) in board and board[(x[0]-1, x[1]-1)][0].endswith(ennemy_number):
                orders.append("{}-{}:*{}-{}".format(x[0], x[1], x[0]-1, x[1]-1))
            if (x[0]-1, x[1]+1) in board and board[(x[0]-1, x[1]+1)][0].endswith(ennemy_number):
                orders.append("{}-{}:*{}-{}".format(x[0], x[1], x[0]-1, x[1] +1))
            if (x[0]+1, x[1]-1) in board and board[(x[0]+1, x[1]-1)][0].endswith(ennemy_number):
                orders.append("{}-{}:*{}-{}".format(x[0], x[1], x[0]+1, x[1]-1))
            if (x[0]+1, x[1]+1) in board and board[(x[0]+1, x[1]+1)][0].endswith(ennemy_number):
                orders.append("{}-{}:*{}-{}".format(x[0], x[1], x[0]+1, x[1] +1))

    #pacify
    #lives est une boucle qui recupere les energy du loup inferieur à 50 et le met dans une liste
    lives = [board[x][1] for x in wolves if board[x][1] <= 50 ]
    print(lives)
    #si la moitier ou plus de loup ont une energy inférieur ou égale à 50 alors on pacifie
    if len(lives) >= len(wolves)//2:
        #get omega
        omega = [x for x in wolves if board[x][0].startswith("omega")][0]
        orders.append("{}-{}:pacify".format(omega[0], omega[1]))
        #apres avoir pacifier il va se nourrir et min food renvoie la liste des positions de nourritures les plus proches d'omega
        min_food = [(x, compute_distance(omega[0], omega[1], x[0], x[1])) for x in board if board[x][0] in foods]
        #sorted pour trier de maniere croissante la liste
        min_food = sorted(min_food, key=lambda x: x[1])
        min_food = min_food[0]
        orders.append("{}-{}:<{}-{}".format(omega[0], omega[1], min_food[0][0], min_food[0][1]))
        
    
    
    # Move
    for x in wolves:
        orders.append("{}-{}:@{}-{}".format(x[0], x[1], x[0] + direction[0], x[1] + direction[1]))

    for o in orders[:9]:
        print(o)
    
    return ",".join(orders[:9])
    
    

# -- coding: utf-8 --

import blessed, math, os, time

term = blessed.Terminal()


# other functions
def stop_game(board):
    """A function to verify if an alpha is dead
    Parameters
    ----------
    board: data structure of game(dict)

    Returns
    -------
    true or false:(bool)
    version:
    _______
    specification: Agathe Mekoui(v.1 25/03/2022)
    implementation: Agathe Mekoui(v.1 25/03/2022)


    """
    alphas = [board[x] for x in board if board[x][0].startswith("alpha")]  # Get all the alphas
    if alphas[0][1] <= 0 or alphas[1][1] <= 0:  # check if an alpha is dead
        display_board(board_dict=board)
        print("termine!!!")
        return True
    return False


# main function
def play_game(map_path, group_1, type_1, group_2, type_2):
    """Play a game.
    
    Parameters
    ----------
    map_path: path of map file (str)
    group_1: group of player 1 (int)
    type_1: type of player 1 (str)
    group_2: group of player 2 (int)
    type_2: type of player 2 (str)
    
    Notes
    -----
    Player type is either 'human', 'AI' or 'remote'.
    
    If there is an external referee, set group id to 0 for remote player.
    version:
    _______
    
    implementation: Agathe Mekoui, Ritaz Mhia, Menie Ngongo Tshimwanga(v.1 25/03/2022)

    
    """

    board, dim = get_data_structure(map_path)

    # create connection, if necessary
    if type_1 == 'remote':
        connection = create_connection(group_2, group_1)
    elif type_2 == 'remote':
        connection = create_connection(group_1, group_2)

    ...
    ...
    tour = 0

    while tour < 200 and not stop_game(board):

        # get orders of player 1 and notify them to player 2, if necessary
        if type_1 == 'remote':
            orders_1 = get_remote_orders(connection)
        else:
            if type_1 == "human":
                orders_1 = input("write exactly syntax of your orders player 1: ")
            elif type_1 == 'AI':
                orders_1 = get_AI_orders(board, dim, 1)
            elif type_2 == 'remote':
                notify_remote_orders(connection, orders_1)
        for order in orders_1.split(","):
            if get_orders(order)[0] != []:
                sp = order.split(':pacify')
                x_y_paci = sp[0].split('-')
                pacification((int((x_y_paci)[0]), int((x_y_paci)[1])), board)

            if get_orders(order)[1] != []:
                spli = order.split(':<')
                x_y_1 = spli[0].split('-')
                x_y_2 = spli[1].split('-')

                feed((int((x_y_1)[0]), int((x_y_1)[1])), (int((x_y_2)[0]), int((x_y_2)[1])), board)

            if get_orders(order)[2] != []:
                splitx = order.split(':*')
                x__y_1 = splitx[0].split('-')
                x__y_2 = splitx[1].split('-')

                attack((int((x__y_1)[0]), int((x__y_1)[1])), (int((x__y_2)[0]), int((x__y_2)[1])), board, [])
            if get_orders(order)[3] != []:
                splitxx = order.split(':@')
                x__y__1 = splitxx[0].split('-')
                x__y__2 = splitxx[1].split('-')

                move_wolf((int((x__y__1)[0]), int((x__y__1)[1])), (int((x__y__2)[0]), int((x__y__2)[1])), board, dim)

        # get orders of player 2 and notify them to player 1, if necessary
        if type_2 == 'remote':
            orders_2 = get_remote_orders(connection)
        else:
            if type_2 == "human":
                orders_2 = input("write exactly syntax of your orders player 2: ")
            elif type_2 == 'AI':
                orders_2 = get_AI_orders(board, dim, 2)
            elif type_2 == 'remote':
                notify_remote_orders(connection, orders_1)

        for order in orders_2.split(","):
            if get_orders(order)[0] != []:
                sp = order.split(':pacify')
                x_y_paci = sp[0].split('-')
                pacification((int((x_y_paci)[0]), int((x_y_paci)[1])), board)

            if get_orders(order)[1] != []:
                spli = order.split(':<')
                x_y_1 = spli[0].split('-')
                x_y_2 = spli[1].split('-')

                feed((int((x_y_1)[0]), int((x_y_1)[1])), (int((x_y_2)[0]), int((x_y_2)[1])), board)

            if get_orders(order)[2] != []:
                splitx = order.split(':*')
                x__y_1 = splitx[0].split('-')
                x__y_2 = splitx[1].split('-')

                attack((int((x__y_1)[0]), int((x__y_1)[1])), (int((x__y_2)[0]), int((x__y_2)[1])), board, [])
            if get_orders(order)[3] != []:
                splitxx = order.split(':@')
                x__y__1 = splitxx[0].split('-')
                x__y__2 = splitxx[1].split('-')

                move_wolf((int((x__y__1)[0]), int((x__y__1)[1])), (int((x__y__2)[0]), int((x__y__2)[1])), board,
                          dim)

        display_board(board)
        tour += 1

    # close connection, if necessary
    if type_1 == 'remote' or type_2 == 'remote':
        close_connection(connection)
    somme_1= 0
    somme_2=0  
    for x in board:
        if board[x][0].endswith(str(1)):
            somme_1+=board[x][1]
        
        elif board[x][0].endswith(str(2)):
            somme_2+=board[x][1]
    print(somme_1,somme_2)
    if somme_1>somme_2:
        print('team1 wins')
    elif somme_1<somme_2:
        print('team2 wins')
    elif somme_1==somme_2:
        print('draw')
            


play_game("README_gr_xx.txt", 1, 'AI', 2, 'AI')
