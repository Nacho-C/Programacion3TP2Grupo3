package modelo;

public class Hielo extends Pokemon
{
	private static final long serialVersionUID = 1L;
	private boolean granRecarga;

	public Hielo(String nombre, boolean granRecarga)
	{
		super(nombre, 500, 100, 120);
		this.granRecarga = granRecarga;
	}

	@Override
	public void recarga()
	{
		if (granRecarga)
		{
			this.fuerza = 400;
		}
		else
		{
			this.fuerza *= 1.1;
			this.vitalidad *= 1.1;
		}
	}

	@Override
	public void golpeFinal(Pokemon otro)
	{
		otro.recibeDano(this.fuerza * 0.9);
		if (this.granRecarga)
			this.fuerza = 100;
	}

	@Override
	public void recibeDano(double dano)
	{
		if (this.escudo >= dano * 0.75)
		{
			this.vitalidad -= dano * 0.25;
			this.escudo -= dano * 0.75;
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
		this.fuerza *= 0.7;
	}

	@Override
	public void recibeViento()
	{
		// System.out.println("El hechizo Viento no afecta a los Pokemon de tipo
		// Hielo.");
	}

	@Override
	public void recibeTormenta()
	{
		this.escudo *= 0.75;
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
		return super.toString() + " (Hielo)";
	}
}
