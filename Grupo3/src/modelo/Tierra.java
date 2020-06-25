package modelo;

public class Tierra extends Pokemon
{
	private static final long serialVersionUID = 1L;

	public Tierra(String nombre)
	{
		super(nombre,700,20,150);
	}

	@Override
	public void golpeFinal(Pokemon otro)
	{
		otro.recibeDano(this.fuerza * 3);
	}

	@Override
	public void recibeDano(double dano)
	{
		if (this.escudo >= dano * 0.8)
		{
			this.vitalidad -= dano * 0.2;
			this.escudo -= dano * 0.8;
		}
		else
		{
			this.vitalidad -= dano - this.escudo;
			this.escudo = 0;
		}
	}

	@Override
	public void recibeNiebla()
	{
	}

	@Override
	public void recibeViento()
	{
		this.escudo *= 0.66;
	}

	@Override
	public void recibeTormenta()
	{
		this.vitalidad *= 0.85;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException
	{
		throw new CloneNotSupportedException("Los pokemon de tipo Tierra no pueden ser clonados.");
	}

	@Override
	public void curar()
	{
		this.vitalidad = 700;
	}
	
	@Override
	public String toString()
	{
		return super.toString() + " (Tierra)";
	}
}
