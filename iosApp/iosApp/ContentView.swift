import SwiftUI
import shared

struct ContentView: View {
    @ObservedObject private(set) var viewModel: MovieViewModel = MovieViewModel()
    
	let greet = Greeting().greeting()
	var body: some View {
        List(viewModel.movieItem ?? [], id: \.id) { item in
            VStack {
                MovieRow(movieItem: item)
            }
            
        }
    }
}
