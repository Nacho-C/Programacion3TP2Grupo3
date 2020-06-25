package modelo;

public class Agua extends Pokemon
{
	private static final long serialVersionUID = 1L;

	public Agua(String nombre)
	{
		super(nombre, 500, 120, 100);
	}

	@Override
	public void recarga()
	{
		this.fuerza *= 1.1;
		this.vitalidad *= 1.1;
	}

	@Override
	public void golpeFinal(Pokemon otro)
	{
		otro.recibeDano(this.fuerza);
		this.fuerza /= 2;
	}

	@Override
	public void recibeDano(double dano)
	{
		if (this.escudo >= dano)
			this.escudo -= dano;
		else
		{
			this.vitalidad -= dano - this.escudo;
			this.escudo = 0;
		}
	}

	@Override
	public void recibeNiebla()
	{
		this.fuerza *= 0.8;
	}

	@Override
	public void recibeViento()
	{
		this.escudo *= 0.85;
	}

	@Override
	public void recibeTormenta()
	{
	}

	@Override
	public Object clone()
	{
		try
		{
			return super.clone();
		}
		catch (CloneNotSupportedException e)
		{
			// Nunca se ejecuta ya que el Pokemon siempre es clonable
			return null;
		}
	}

	@Override
	public void curar()
	{
		this.vitalidad = 500;
	}

	@Override
	public String toString()
	{
		return super.toString() + " (Agua)";
	}
}
