import SwiftUI
import shared

struct ContentView: View {
    @ObservedObject private(set) var viewModel: MovieViewModel = MovieViewModel()
    
    @State var searchQuery = "서울"
    @FocusState private var searchFieldFocusState: Bool
	let greet = Greeting().greeting()
	var body: some View {
        VStack {
            TextField(
                "영화 제목을 검색해주세용",
                text: $searchQuery
            )
            .onSubmit {
                viewModel.searchMovie(searchQuery: searchQuery)
            }
            .focused($searchFieldFocusState)
            .textInputAutocapitalization(.never)
            .disableAutocorrection(true)
            .textFieldStyle(.roundedBorder)
            .padding(8)
            .border(searchFieldFocusState ? Color.purple : Color.gray)
            .background(Color(uiColor: .secondarySystemBackground))
            .onChange(of: searchQuery) {
                viewModel.searchMovie(searchQuery: $0)
            }
    
            
            List(viewModel.movieItem ?? [], id: \.id) { item in
                VStack {
                    MovieRow(movieItem: item)
                }
                
            }
        }
        
    }
}
