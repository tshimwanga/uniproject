from engine_gr_30 import *
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