

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* Aluno: Julio John Tavares Ramos / Ra: 200481
*/

public class Main {

	public static void main(String[] args) {
		
		Usuario u0 = new Usuario("u0", "tinky@hotmail.com", "123456", true);
		Usuario u1 = new Usuario("u1", "laalaa@hotmail.com", "123456", true);
		Usuario u2 = new Usuario("u2", "dipsy666@hotmail.com", "000666", true);
		Usuario u3 = new Usuario("u3", "po@hotmail.com", "123654", true);
		Usuario u4 = new Usuario("u4", "j4c@hotmail.com", "12156", true);
		
		Perfil perfil0 = new Perfil('F', "15/04/1780", "Sao Paulo", "Sao Paulo", "1215-1620", true, 3.1f, u0);
		Perfil perfil1 = new Perfil('M', "15/05/1780", "Sao Paulo", "Sao Paulo", "1487-261", true, 4.8f, u1);
		Perfil perfil2 = new Perfil('M', "15/06/1780", "Sao Paulo", "Sao Paulo", "11-14158425", true, 9.6f, u2);
		Perfil perfil3 = new Perfil('F', "15/07/1780", "Sao Paulo", "Sao Paulo", "18-2516254", true, 7.4f, u3);
		Perfil perfil4 = new Perfil('M', "15/07/1958", "China", "Xablau", "66-2516254", true, 9.9f, u4);
		
		Caroneiro caroneiro0 = new Caroneiro("1415 1914 2021 2487", perfil0);
		Caroneiro caroneiro1 = new Caroneiro("2154 1847 3569 4781", perfil1);
		Caroneiro caroneiro2 = new Caroneiro("2154 1847 3569 4781", perfil2);
		Caroneiro caroneiro3 = new Caroneiro("1487 9765 4566 6661", perfil3);
		Caroneiro caroneiro4 = new Caroneiro("2154 1847 3569 4781", perfil4);
		
		Caronante caronante0 = new Caronante(10, "Funkao", "ABC 1234", "KXER1458S-1", "Ford", "K", perfil0);
		Caronante caronante1 = new Caronante(10, "Funkao", "ABC 1234", "KXER1458S-1", "Ford", "K", perfil1);
		Caronante caronante2 = new Caronante(10, "Funkao", "ABC 1234", "KXER1458S-1", "Ford", "K", perfil2);
		Caronante caronante3 = new Caronante(10, "Funkao", "ABC 1234", "KXER1458S-1", "Ford", "K", perfil3);
		Caronante caronante4 = new Caronante(10, "Funkao", "ABC 1234", "KXER1458S-1", "Ford", "K", perfil4);
		
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		usuarios.add(u0);
		usuarios.add(u1);
		usuarios.add(u2);
		usuarios.add(u3);
		usuarios.add(u4);
			
		GrupoPublico gpu = new GrupoPublico(1, "gpu", "soh os otakao");
		GrupoPrivado EC017 = u0.criarGrupoPrivado(2, "EC017", "soh os xerosos");
		
		System.out.println(EC017); //ok
		u0.adicionarUsuarioAUmGrupo(EC017, u0, u1);
		u0.adicionarUsuarioAUmGrupo(EC017, u0, u2);
		System.out.println(EC017); //ok
		
		System.out.println(EC017); //ok
		u2.adicionarUsuarioAUmGrupo(EC017, u2, u4);
		System.out.println(EC017); //ok 
		
		System.out.println(gpu);
		u2.adicionarGrupo(gpu);
		u3.adicionarGrupo(gpu);
		u4.adicionarGrupo(gpu);
		System.out.println(gpu);
		
		CaronaPublica novaCarona = (CaronaPublica)u3.getPerfil().getCaronante().oferecerCaronaPublica(1.2, 2.4, 5.4, 1.7, "20:51", 4, 75);
		novaCarona.adicionarGrupo(gpu);
		
		u0.removerGrupo(gpu);
		
		//carona criada pelo u2
		CaronaPrivada novaCaronaB = (CaronaPrivada) u2.getPerfil().getCaronante().oferecerCaronaPrivada(2.3, 5.2, 4.1, 2.8, "12:32", 3, 40);
		System.out.println(novaCaronaB.adicionarGrupo(EC017));
		
		//carona criada pelo u4
		CaronaPrivada novaCaronaC = (CaronaPrivada) u4.getPerfil().getCaronante().oferecerCaronaPrivada(3.2, 7.2, 1.4, 8.4, "14:32", 4, 90);
		System.out.println(novaCaronaC.adicionarGrupo(EC017));
		
		System.out.println(novaCaronaB.AdicionarCaroneiro(caroneiro0));
		System.out.println(novaCaronaB.AdicionarCaroneiro(caroneiro1));
		System.out.println(novaCaronaB.AdicionarCaroneiro(caroneiro3));
		
		List<Perfil> perfies = new ArrayList<Perfil>();
		perfies.add(perfil0);
		perfies.add(perfil1);
		perfies.add(perfil2);
		Collections.sort(perfies);
		
		System.out.println();
		System.out.println();
		
		//printando em ordem de usuarios
		for(int i = 0; i < usuarios.size(); i++)
		{
			System.out.println(usuarios.get(i));
		}
		
		//pritando em ordem de perfies
		for(int i = 0; i < perfies.size(); i++)
		{
			System.out.println(perfies.get(i));
		}
		
		for(int j = 0; j < usuarios.size(); j++)
		{
			usuarios.get(j).salvarParaArquivo();
		}
	}
}