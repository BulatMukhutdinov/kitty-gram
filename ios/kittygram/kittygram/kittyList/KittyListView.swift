import SwiftUI
import shared

struct KittyListView: View {
    
    @ObservedObject
    var viewModel = KittyListViewModel()
    
    var body: some View {
        NavigationView {
            List(viewModel.kitties, id: \.breed) { kitty in
                NavigationLink(destination: KittyProfileView(kitty: kitty)){
                    KittyListItemView(kitty: kitty)
                }
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
