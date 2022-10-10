import SwiftUI
import shared

struct ContentView: View {
    var viewModel: MovieViewModel = MovieViewModel()
	let greet = Greeting().greeting()
	var body: some View {
        List(viewModel.movieList , id: \.self) { movieItem in
            Text(movieItem.title)
        }.onAppear {
            viewModel.fetchMovies()
        }
    }
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
