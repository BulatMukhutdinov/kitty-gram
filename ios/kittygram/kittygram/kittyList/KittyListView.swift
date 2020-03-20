import SwiftUI
import shared
import SwiftUIRefresh

struct KittyListView: View {
    
    @ObservedObject
    var viewModel = KittyListViewModel()
    
    @State
    private var isRefreshing = false
    
    var body: some View {
        NavigationView {
            List(viewModel.kitties, id: \.breed) { kitty in
                NavigationLink(destination: KittyProfileView(kitty: kitty)){
                    KittyListItemView(kitty: kitty)
                }
            }
            .pullToRefresh(isShowing: $isRefreshing) {
                self.viewModel.refresh()
                self.isRefreshing = false
            }
            .navigationBarTitle(Text("Kitties"))
        }
    }
    
    struct KittyListView_Previews: PreviewProvider {
        
        static var previews: some View {
            KittyListView()
        }
    }
}
