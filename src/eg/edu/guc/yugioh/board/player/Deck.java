
package eg.edu.guc.yugioh.board.player;


import eg.edu.guc.yugioh.cards.*;
import eg.edu.guc.yugioh.cards.spells.*;
import eg.edu.guc.yugioh.exceptions.*;
import CSV.*;

import java.io.*;

import java.util.*;

public class Deck{

	private static ArrayList<Card> monsters;
	private static ArrayList<Card> spells;
	private ArrayList<Card> deck;
	private static String monstersPath = "Database-Monsters.csv";
	private static String spellsPath = "Database-Spells.csv";

	public Deck() throws Exception
	{
		monsters = loadCardsFromFile(monstersPath);
		spells = loadCardsFromFile(spellsPath);
		this.buildDeck(monsters, spells);
		this.shuffleDeck();



	}
	public ArrayList<Card> loadCardsFromFile(String path) throws Exception
	{
		Scanner sc=new Scanner(System.in);
		String currentLine = "";
		FileReader fileReader;
		BufferedReader br = null;
		ArrayList<Card> card=null;
		boolean done=false;
		int line;
		String tmp[];

		int i=0;
		do{
			try
			{
				i+=1;
				card=new ArrayList();
				fileReader= new FileReader(path);
				br = new BufferedReader(fileReader);
				line=0;
				
				while ((currentLine = br.readLine()) != null) 
				{
					line++;
					tmp  = currentLine.split(",");
					if ((tmp[0].equals("Spell")&&tmp.length!=3)||(tmp[0].equals("Monster")&&tmp.length!=6))
					{
						throw new MissingFieldException(path, line);
					}
			
					for(int j=0;j<tmp.length;j++)
					{		
						if(empty(""+tmp[j]))
							throw new EmptyFieldException(path, line,(j+1));
					}
					if(tmp[0].equals("Monster"))
					{
						card.add(new MonsterCard(tmp[1], tmp[2], Integer.parseInt(tmp[5]),Integer.parseInt(tmp[3]),Integer.parseInt(tmp[4])));
						card.get(card.size()-1).setLocation(Location.DECK);
					}
					else if(tmp[0].equals("Spell"))
					{
						switch(tmp[1])
						{
						case "Card Destruction": card.add(new CardDestruction(tmp[1],tmp[2])); break;
						case "Change Of Heart":	card.add(new ChangeOfHeart(tmp[1],tmp[2])); break;
						case "Dark Hole": card.add(new DarkHole(tmp[1],tmp[2])); break;
						case "Graceful Dice": card.add(new GracefulDice(tmp[1],tmp[2])); break;
						case "Harpie's Feather Duster": card.add(new HarpieFeatherDuster(tmp[1],tmp[2])); break;
						case "Heavy Storm": card.add(new HeavyStorm(tmp[1],tmp[2])); break;
						case "Mage Power": card.add(new MagePower(tmp[1],tmp[2])); break;
						case "Monster Reborn": card.add(new MonsterReborn(tmp[1],tmp[2])); break;
						case "Pot of Greed": card.add(new PotOfGreed(tmp[1],tmp[2])); break;
						case "Raigeki": card.add(new Raigeki(tmp[1],tmp[2])); break;
						default: throw new UnknownSpellCardException(path,line,tmp[1]);
						}
						card.get(card.size()-1).setLocation(Location.DECK);
					}
					else
					{
						throw new UnknownCardTypeException(path,line,tmp[0]);	
					}

				}
				done=true;
			}

			catch (Exception e)
			{
				
				if(i<=3){
					if(e instanceof FileNotFoundException)
					{
						System.out.println("The file was not found");
						//throw e;
					}
					if(e instanceof MissingFieldException)
					{
						System.out.println("Missing Field");
					}
					if(e instanceof EmptyFieldException)
					{
						System.out.println("Empty Field");
					}
					if(e instanceof UnknownCardTypeException)
					{
						System.out.println("Unknown Card type");
					}
					if(e instanceof UnknownSpellCardException)
					{
						System.out.println("Unknown Spell Card");
					}
					
					
					System.out.print("Please enter a correct path: ");
					path=sc.nextLine();	
				}
				else
				{
					e.printStackTrace();
					throw e;
				}
			}
		}

		while(i<=3&&!done);

			return card;

	}



	private void buildDeck(ArrayList<Card> monsters, ArrayList<Card> spells)
	{
		this.deck = new ArrayList();

		for (int i=0;(i<15);i++)
		{
			int rand = (int) (Math.random()*monsters.size());	
			MonsterCard monster = (MonsterCard) monsters.get(rand);

			MonsterCard clone = new MonsterCard(monster.getName(),
					monster.getDescription(), monster.getLevel(),
					monster.getAttackPoints(), monster.getDefensePoints());
			clone.setMode(monster.getMode());
			clone.setHidden(monster.isHidden());
			clone.setLocation(Location.DECK);
			deck.add(clone);
		}
		for (int i=0;(i<5);i++)
		{
			int rand = (int) (Math.random()*spells.size());
			SpellCard spell = (SpellCard) spells.get(rand);


			if(spell instanceof DarkHole){
				DarkHole clone = new DarkHole(spell.getName(),
						spell.getDescription());
				clone.setHidden(spell.isHidden());
				clone.setLocation(Location.DECK);
				deck.add(clone);}
			 else if(spell instanceof Raigeki){
				Raigeki clone = new Raigeki(spell.getName(),
						spell.getDescription());
				clone.setHidden(spell.isHidden());
				clone.setLocation(Location.DECK);
				deck.add(clone);}
			else if(spell instanceof HeavyStorm){
				HeavyStorm clone = new HeavyStorm(spell.getName(),
						spell.getDescription());
				clone.setHidden(spell.isHidden());
				clone.setLocation(Location.DECK);
				deck.add(clone);}
			else if(spell instanceof HarpieFeatherDuster){
				HarpieFeatherDuster clone = new HarpieFeatherDuster(spell.getName(),
						spell.getDescription());
				clone.setHidden(spell.isHidden());
				clone.setLocation(Location.DECK);
				deck.add(clone);}
			else if(spell instanceof CardDestruction){
				CardDestruction clone = new CardDestruction(spell.getName(),
						spell.getDescription());
				clone.setHidden(spell.isHidden());
				clone.setLocation(Location.DECK);
				deck.add(clone);}
			else if(spell instanceof ChangeOfHeart){
				ChangeOfHeart clone = new ChangeOfHeart(spell.getName(),
						spell.getDescription());
				clone.setHidden(spell.isHidden());
				clone.setLocation(Location.DECK);
				deck.add(clone);}
			else if(spell instanceof GracefulDice){
				GracefulDice clone = new GracefulDice(spell.getName(),
						spell.getDescription());
				clone.setHidden(spell.isHidden());
				clone.setLocation(Location.DECK);
				deck.add(clone);}
			else if(spell instanceof MagePower){
				MagePower clone = new MagePower(spell.getName(),
						spell.getDescription());
				clone.setHidden(spell.isHidden());
				clone.setLocation(Location.DECK);
				deck.add(clone);}
			else if(spell instanceof MonsterReborn){
				MonsterReborn clone = new MonsterReborn(spell.getName(),
						spell.getDescription());
				clone.setHidden(spell.isHidden());
				clone.setLocation(Location.DECK);
				deck.add(clone);}
			else if(spell instanceof PotOfGreed){
				PotOfGreed clone = new PotOfGreed(spell.getName(),
						spell.getDescription());
				clone.setHidden(spell.isHidden());
				clone.setLocation(Location.DECK);
				deck.add(clone);}


		}
	}
	private void shuffleDeck ()
	{

		for(int i=0;i<20;i++)
		{
			Card tmp=this.deck.remove(i);
			this.deck.add((int)(Math.random()*20), tmp);
		}
	}
	public ArrayList<Card> drawNCards(int n)
	{
		ArrayList<Card> hand = new ArrayList();

		for(int i = 0; i<n;i++){
			if(deck.size()==0)
			{
				Card.getBoard().setWinner(Card.getBoard().getOpponentPlayer());
				break;
			}
			hand.add(deck.remove(deck.size()-1));
			hand.get(hand.size()-1).setLocation(Location.DECK);	
		}
		return hand;

	}
	public Card drawOneCard()
	{
		if(deck.size()==0)
		{
			Card.getBoard().setWinner(Card.getBoard().getOpponentPlayer());
			return null;
		}
		return this.deck.remove(this.deck.size()-1); 

	}
	public ArrayList<Card> getDeck() {
		return deck;
	}

	public static ArrayList<Card> getMonsters() {
		return monsters;
	}
	public static ArrayList<Card> getSpells() {
		return spells;
	}
	public static String getMonstersPath() {
		return monstersPath;
	}
	public static void setMonstersPath(String monstersPath) {
		Deck.monstersPath = monstersPath;
	}
	public static String getSpellsPath() {
		return spellsPath;
	}
	public static void setSpellsPath(String spellsPath) {
		Deck.spellsPath = spellsPath;
	}
	public static boolean empty(String s){
		if(s.equals(""))
			return true;
		else if(s.charAt(0)==' '){
			return empty(s.substring(1));
		}
		else
			return false;
	}
	public static void main(String[] args) throws Exception{
		Deck deck = new Deck();
		for(int i = 0;i<deck.deck.size();i++)
		{
			System.out.println(deck.deck.get(i).getName()+"  "+deck.deck.get(i).getClass());
		}
		


	}



}
