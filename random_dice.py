import random

while True:
	usr=input('hit enter')
	r=random.choice((1,2,3,4,5,6))
	if r==1:
		print('''
		______________
		l                       l
		l                       l
		l         @           l
		l                       l
		l_____________l
		''')
	elif r==2:
		print('''
		______________
		l                       l
		l                       l
		l    @        @    l
		l                       l
		l_____________l
		''')
	elif r==3:
		print('''
		______________
		l                       l
		l                       l
		l  @     @    @  l
		l                       l
		l_____________l
		''')
	elif r==4:
		print('''
		______________
		l                       l
		l   @           @  l
		l                       l
		l   @           @  l
		l_____________l
		''')
	elif r==5:
		print('''
		______________
		l                       l
		l   @          @   l
		l         @          l
		l   @          @   l
		l_____________l
		''')
	elif r==6:
		print('''
		______________
		l                       l
		l  @            @  l
		l  @            @  l
		l  @            @  l
		l_____________l
		''')