package eg.edu.guc.yugioh.cards;

public class MonsterCard extends Card
{
	private int level;
	private int attackPoints;
	private int defensePoints;
	private Mode mode;
	private boolean atk;
	private boolean modeSwitch;



	public MonsterCard(String name, String description, int level, int attackPoints, int defensePoints)
	{
		super(name, description);
		this.level = level;
		this.attackPoints = attackPoints;
		this.defensePoints = defensePoints;
		this.mode = Mode.DEFENSE;
	}



	public boolean isModeSwitch() {
		return modeSwitch;
	}



	public void setModeSwitch(boolean modeSwitch) {
		this.modeSwitch = modeSwitch;
	}



	public int getAttackPoints() {
		return attackPoints;
	}



	public void setAttackPoints(int attackPoints) {
		this.attackPoints = attackPoints;
	}



	public int getDefensePoints() {
		return defensePoints;
	}



	public void setDefensePoints(int defensePoints) {
		this.defensePoints = defensePoints;
	}



	public Mode getMode() {
		return mode;
	}



	public void setMode(Mode mode) {
		this.mode = mode;
	}



	public int getLevel() {
		return level;
	}
	public boolean attacked(){
		return this.atk;
	}
	public void setAtk(boolean atk){
		this.atk = atk;
	}



	@Override
	public void action(MonsterCard monster) 
	{
		this.getBoard().getActivePlayer().declareAttack(this, monster);
	}
	public void action()
	{
		this.getBoard().getActivePlayer().declareAttack(this);
	}
	

	


}
